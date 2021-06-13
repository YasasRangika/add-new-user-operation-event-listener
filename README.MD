# User Operation Event Listener to add some specific role to newly creating users

This User Operation Event Listener to add some specific role to newly creating users (eg: Internal/subscriber).
 
### Steps to deploy
- Build the component by running "mvn clean install"
- Copy following jar file which can be found in target directory into <APIM_HOME>/repository/components/dropins/
    - add some specific role to newly creating users
- Configure the User Operation Event Listener by adding following lines into deployment.toml file.
    ```
    [[event_listener]]
    id = "add-new-user-operation-event-listener"
    type = "org.wso2.carbon.user.core.listener.UserOperationEventListener"
    name = "org.wso2.custom.user.operation.event.listener.AddNewUserOperationEventListener"
    order = 2

    ```
- Restart WSO2 APIM.

### Testing
- Login to management console and try to update/create a new user without Internal/subscriber role.
- You will see it has been added Internal/subscriber role to the user. (View user roles by clicking on 'View Roles')
