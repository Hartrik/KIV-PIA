package cz.hartrik.pia.dto

/**
 * Base interface for all data transfer objects.
 *
 * @version 2018-11-17
 * @author Patrik Harag
 */
interface DataTransferObject<PK extends Serializable> {

    /**
     *
     * @return  primary key of the instance
     */
    PK getPK()

}
