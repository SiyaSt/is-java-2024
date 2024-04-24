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
