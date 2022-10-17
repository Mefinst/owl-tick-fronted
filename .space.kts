/**
* JetBrains Space Automation
* This Kotlin-script file lets you automate build activities
* For more info, see https://www.jetbrains.com/help/space/automation.html
*/

job("Unit tests") {    
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
}

job("Docker image") {
    resources {
        cpu = 1.cpu
        memory = 1000.mb
    }    
    // get auth data from secrets and put it to env vars
    env["DOCKER_USER"] = "token"
    env["DOCKER_TOKEN"] = Secrets("DOCKER_REGISTRY_TOKEN")

    // put auth data to Docker config
    beforeBuildScript {
        content = """
                B64_AUTH=${'$'}(echo -n ${'$'}DOCKER_USER:${'$'}DOCKER_TOKEN | base64 -w 0)
                echo "{\"auths\":{\"https://cr.selcloud.ru/v1/\":{\"auth\":\"${'$'}B64_AUTH\"}}}" > ${'$'}DOCKER_CONFIG/config.json
            """
    }
    docker {
        build {
        }
        push("cr.selcloud.ru/mefinst/owl-tick-frontend") {
        	tags("space-test")
        }
    }
}
