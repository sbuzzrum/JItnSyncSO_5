/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ITN_SYNC_SO.ws.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author lucio
 */
public class Request extends XmlUtil {

    // ===== Modello Dati ======================================================
    public static final String NAMESPACE = "urn:sap.com:proxy:PRD:/1SAI/TXS3A4B2F48D553AFEB6A7D:700:2008/06/25";

    private final List<Carico> listaCarichi = new ArrayList<>();

    public Request addCarico(Carico c) {
        listaCarichi.add(Objects.requireNonNull(c, "carico"));
        return this;
    }

    public List<Carico> getListaCarichi() {
        return listaCarichi;
    }

    /**
     * Valida ricorsivamente i dati (lancia IllegalArgumentException se non
     * valido).
     */
    public void validate() {
        if (listaCarichi.isEmpty()) {
            throw new IllegalArgumentException("listaCarichi non può essere vuota");
        }
        for (Carico c : listaCarichi) {
            c.validate();
        }
    }

    /**
     * Serializza l'intero documento XML UTF-8 con namespace di default.
     */
    public String toXml() {
        validate();
        StringBuilder sb = new StringBuilder(1024);
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<KitFerri.Import.Request xmlns=\"").append(escapeAttr(NAMESPACE)).append("\">");
        sb.append("<listaCarichi>");
        for (Carico c : listaCarichi) {
            c.appendXml(sb);
        }
        sb.append("</listaCarichi>");
        sb.append("</KitFerri.Import.Request>");
        return sb.toString();
    }
}
