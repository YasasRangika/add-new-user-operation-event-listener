package org.wso2.custom.user.operation.event.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.core.AbstractIdentityUserOperationEventListener;
import org.wso2.carbon.identity.core.util.IdentityCoreConstants;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.carbon.user.core.UserStoreManager;

import java.util.*;

/**
 * A userstore operation event listener to add Internal/subscriber role to every new user if missed to add the role
 * when the time of new user creation.
 */
public class AddNewUserOperationEventListener extends AbstractIdentityUserOperationEventListener {

    private static final Log log = LogFactory.getLog(AddNewUserOperationEventListener.class);
    private static final String SUBSCRIBER_ROLE = "Internal/subscriber";


    @Override
    public int getExecutionOrderId() {

        int orderId = getOrderId();
        if (orderId != IdentityCoreConstants.EVENT_LISTENER_ORDER_ID) {
            return orderId;
        }
        // This listener should be executed before all the other listeners.
        // 0 and 1 are already reserved for audit loggers, hence using 2.
        return 2;
    }

    @Override
    public boolean doPreAddUser(String userName, Object credential, String[] roleList, Map<String, String> claims,
                                String profile, UserStoreManager userStoreManager) throws UserStoreException {
        log.info("============= doPreAddUser method started!! =================");
        List<String> roles = new ArrayList<>(Arrays.asList(roleList));
        if (!roles.isEmpty() && !roles.contains(SUBSCRIBER_ROLE)) {
            userStoreManager.updateRoleListOfUser(userName, new String[]{}, new String[] { SUBSCRIBER_ROLE });
        }
        return true;
    }
}
