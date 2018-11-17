package cz.hartrik.pia.controller

import org.junit.Test

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 *
 * @version 2018-11-17
 * @author Patrik Harag
 */
class PageNotFoundTest extends HelperAbstractMvcTest {

    @Test
    void notFound() {
        get("/lorem-ipsum").andExpect(status().is(404))
    }

}
