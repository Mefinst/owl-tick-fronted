/**
* JetBrains Space Automation
* This Kotlin-script file lets you automate build activities
* For more info, see https://www.jetbrains.com/help/space/automation.html
*/

job("Unit tests") {
    container(displayName = "Unit tests", image = "node:lts") {
    	shellScript {
            name = "Retrieve dependencies"
            interpreter = "/bin/sh"
            content = """
                npm ci
            """
        }
        shellScript {
            name = "Run tests"
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
