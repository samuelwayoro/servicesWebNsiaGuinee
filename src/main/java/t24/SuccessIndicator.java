
package t24;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour successIndicator.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="successIndicator">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Success"/>
 *     &lt;enumeration value="TWSError"/>
 *     &lt;enumeration value="T24Error"/>
 *     &lt;enumeration value="T24Override"/>
 *     &lt;enumeration value="T24Offline"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "successIndicator")
@XmlEnum
public enum SuccessIndicator {

    @XmlEnumValue("Success")
    SUCCESS("Success"),
    @XmlEnumValue("TWSError")
    TWS_ERROR("TWSError"),
    @XmlEnumValue("T24Error")
    T_24_ERROR("T24Error"),
    @XmlEnumValue("T24Override")
    T_24_OVERRIDE("T24Override"),
    @XmlEnumValue("T24Offline")
    T_24_OFFLINE("T24Offline");
    private final String value;

    SuccessIndicator(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SuccessIndicator fromValue(String v) {
        for (SuccessIndicator c: SuccessIndicator.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
