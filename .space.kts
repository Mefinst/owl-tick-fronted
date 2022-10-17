/**
* JetBrains Space Automation
* This Kotlin-script file lets you automate build activities
* For more info, see https://www.jetbrains.com/help/space/automation.html
*/

job("Unit tests") {
    container(displayName = "Get dependencies", image = "node:lts") {
    	shellScript(displayName = "Retrieve dependencies") {
            
            interpreter = "/bin/sh"
            content = """
                npm ci
            """
        }
    }
    container(displayName = "Unit tests", image = "node:lts") {
        shellScript(displayName = "Run tests") {
            interpreter = "/bin/sh"
            content = """
                npm run tests
            """
        }
    }
}

job("Docker image") {
    docker {
        build {
        }
    }
}
