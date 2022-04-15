resource "azurerm_resource_group" "example" {
  name     = "ansible"
  location = "Central India"
}

resource "azurerm_app_service_plan" "example" {
  name                = "java-app-serviceplan"
  location            = azurerm_resource_group.example.location
  resource_group_name = azurerm_resource_group.example.name
  kind                = "Windows"
  reserved            = true

  sku {
    tier = "Free"
    size = "F1"
  }
}

resource "azurerm_app_service" "example" {
  name                = "java-terraform-app"
  location            = azurerm_resource_group.example.location
  resource_group_name = azurerm_resource_group.example.name
  app_service_plan_id = azurerm_app_service_plan.example.id
   site_config         = [{
                            java_version           = "11"
                            java_container         = "JAVA"
                            java_container_version = "11"
                          }]
}
