<<<<<<< HEAD
package com.spring.cloud.product.facade;
import com.spring.cloud.common.pojo.UserInfo;
import com.spring.cloud.common.vo.ResultMessage;
public interface R4jFacade {
    public ResultMessage exp(String msg);

    public UserInfo getUser(Long id);

    public UserInfo getUser3(Long id);

    public ResultMessage exp();

    public UserInfo cacheUserInfo(Long id);

    public ResultMessage timeout();

    public UserInfo mixUserInfo(Long id);
=======
package com.spring.cloud.product.facade;
import com.spring.cloud.common.pojo.UserInfo;
import com.spring.cloud.common.vo.ResultMessage;
public interface R4jFacade {
    public ResultMessage exp(String msg);

    public UserInfo getUser(Long id);

    public UserInfo getUser3(Long id);

    public ResultMessage exp();

    public UserInfo cacheUserInfo(Long id);

    public ResultMessage timeout();

    public UserInfo mixUserInfo(Long id);
>>>>>>> ce45c9b3713495949ba406e619e7db16886d0e69
}