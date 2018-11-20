package cz.hartrik.pia.config

import cz.hartrik.pia.service.TuringTest
import cz.hartrik.pia.service.TuringTestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

import javax.annotation.PostConstruct

/**
 *
 * @version 2018-11-20
 * @author Patrik Harag
 */
@Configuration
class TuringTestPopulator {

    @Autowired
    TuringTestService turingTestService

    @PostConstruct
    void populate() {
        // just for an example...
        turingTestService.register(new TuringTest() {
            @Override
            String getId() {
                return 'question-byte'
            }

            @Override
            String getQuestion() {
                '2 ^ 8 = ?'
            }

            @Override
            boolean test(String response) {
                response?.trim() == '256'
            }
        })
    }
}