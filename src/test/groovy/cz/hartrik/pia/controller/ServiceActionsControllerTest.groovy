package cz.hartrik.pia.controller

import org.junit.Test

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 *
 * @version 2018-11-17
 * @author Patrik Harag
 */
class ServiceActionsControllerTest extends HelperAbstractMvcTest {

    @Test
    void notLogged() {
        get("/service/a/remove-user?id=1").andExpect(status().isUnauthorized())
    }

}
