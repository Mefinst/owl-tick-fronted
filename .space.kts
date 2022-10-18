/**
* JetBrains Space Automation
* This Kotlin-script file lets you automate build activities
* For more info, see https://www.jetbrains.com/help/space/automation.html
*/

job("Frontend build") {    
    container(displayName = "Unit tests", image = "node:lts") {
        resources {
            cpu = 1.cpu
            memory = 1000.mb
        }
        shellScript(displayName = "Run tests") {
            interpreter = "/bin/sh"
            content = """
                npm ci && npm run test
            """
        }
    }
    
    val imageName = Params("frontend_docker_image_name").toString()
    docker(displayName = "Build docker image") {
        resources {
            cpu = 1.cpu
            memory = 1000.mb
        }    
        

        // get auth data from secrets and put it to env vars
        env["DOCKER_USER"] = Secrets("docker_registry_user")
        env["DOCKER_TOKEN"] = Secrets("docker_registry_token")
        env["DOCKER_IMAGE_NAME"] = Params("frontend_docker_image_name")
        // put auth data to Docker config
        beforeBuildScript {
            content = """
                    B64_AUTH=${'$'}(echo -n ${'$'}DOCKER_USER:${'$'}DOCKER_TOKEN | base64 -w 0)
                    echo "{\"auths\":{\"https://cr.selcloud.ru/v1/\":{\"auth\":\"${'$'}B64_AUTH\"}}}" > ${'$'}DOCKER_CONFIG/config.json
                """
        }
        
        build {
        }
        push(imageName) {
        	tags("space-test")
        }
    }
}
