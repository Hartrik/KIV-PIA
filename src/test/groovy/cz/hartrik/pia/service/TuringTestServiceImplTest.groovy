package cz.hartrik.pia.service

import org.junit.Before
import org.junit.Test

/**
 *
 * @version 2018-11-20
 * @author Patrik Harag
 */
class TuringTestServiceImplTest {

    def service = new TuringTestServiceImpl()
    def test = new TuringTest() {
        @Override
        String getId() {
            return '1'
        }

        @Override
        String getQuestion() {
            'question'
        }

        @Override
        boolean test(String response) {
            response == '42'
        }
    }

    @Before
    void setUp() {
        service.register(test)
    }

    @Test
    void test() {
        def t = service.randomTest()
        assert t != null

        assert service.findTest(t.id) == t
    }

}
