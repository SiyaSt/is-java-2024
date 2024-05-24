plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "labs"
include("lab-1")
include("lab-1:banks-core")
findProject(":lab-1:banks-core")?.name = "banks-core"
include("lab-1:banks-console")
findProject(":lab-1:banks-console")?.name = "banks-console"
include("lab-2")
include("lab-2:model")
findProject(":lab-2:model")?.name = "model"
include("lab-2:service")
findProject(":lab-2:service")?.name = "service"
include("lab-2:console")
findProject(":lab-2:console")?.name = "console"
include("lab-3")
include("lab-3:model")
findProject(":lab-3:model")?.name = "model"
include("lab-3:service")
findProject(":lab-3:service")?.name = "service"
include("lab-3:controller")
findProject(":lab-3:controller")?.name = "controller"
include("lab-4")
include("lab-4:model")
findProject(":lab-4:model")?.name = "model"
include("lab-4:service")
findProject(":lab-4:service")?.name = "service"
include("lab-4:controller")
findProject(":lab-4:controller")?.name = "controller"
include("lab-5")
include("lab-5:cat")
findProject(":lab-5:cat")?.name = "cat"
include("lab-5:cat:cat-app")
findProject(":lab-5:cat:cat-app")?.name = "cat-app"
include("lab-5:cat:cat-client")
findProject(":lab-5:cat:cat-client")?.name = "cat-client"
include("lab-5:owner")
findProject(":lab-5:owner")?.name = "owner"
include("lab-5:owner:owner-app")
findProject(":lab-5:owner:owner-app")?.name = "owner-app"
include("lab-5:owner:owner-client")
findProject(":lab-5:owner:owner-client")?.name = "owner-client"
include("lab-5:gateway")
findProject(":lab-5:gateway")?.name = "gateway"
include("lab-5:cat:cat-model")
findProject(":lab-5:cat:cat-model")?.name = "cat-model"
include("lab-5:owner:owner-model")
findProject(":lab-5:owner:owner-model")?.name = "owner-model"

