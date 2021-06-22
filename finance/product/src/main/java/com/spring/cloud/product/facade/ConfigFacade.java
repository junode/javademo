<<<<<<< HEAD
package com.spring.cloud.product.facade;

import com.spring.cloud.common.pojo.UserInfo;

public interface ConfigFacade {
    public UserInfo getUserWithCircuitBreaker(Long id);

    public UserInfo getUserWithRatelimiter(Long id);
}
=======
package com.spring.cloud.product.facade;

import com.spring.cloud.common.pojo.UserInfo;

public interface ConfigFacade {
    public UserInfo getUserWithCircuitBreaker(Long id);

    public UserInfo getUserWithRatelimiter(Long id);
}
>>>>>>> ce45c9b3713495949ba406e619e7db16886d0e69
