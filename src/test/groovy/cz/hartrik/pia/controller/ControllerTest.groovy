package cz.hartrik.pia.controller


import org.junit.Test

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 *
 * @version 2018-11-15
 * @author Patrik Harag
 */
class ControllerTest extends HelperAbstractMvcTest {

    @Test
    void notFound() {
        get("/lorem-ipsum").andExpect(status().is4xxClientError())
    }

}
