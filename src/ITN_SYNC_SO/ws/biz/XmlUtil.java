/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ITN_SYNC_SO.ws.biz;

/**
 *
 * @author lucio
 */
public abstract class XmlUtil {
    
    // ====== Utilità XML e Validazione ========================================
    protected static void appendTag(StringBuilder sb, String tag, String value) {
        sb.append('<').append(tag).append('>')
                .append(escapeText(value))
                .append("</").append(tag).append('>');
    }

    protected static void appendOptionalTag(StringBuilder sb, String tag, String value) {
        if (value != null && !value.isEmpty()) {
            appendTag(sb, tag, value);
        }
    }

    protected static void requireNotBlank(String s, String field) {
        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " è obbligatorio");
        }
    }

    protected static void requireUnsigned(Long n, String field) {
        if (n == null || n < 0) {
            throw new IllegalArgumentException(field + " deve essere unsigned (>= 0)");
        }
    }

    /**
     * Escape per testo element content.
     */
    protected static String escapeText(String s) {
        StringBuilder out = new StringBuilder((int) (s.length() * 1.2));
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '&':
                    out.append("&amp;");
                    break;
                case '<':
                    out.append("&lt;");
                    break;
                case '>':
                    out.append("&gt;");
                    break;
                default:
                    out.append(c);
            }
        }
        return out.toString();
    }

    /**
     * Escape per attribute value (doppio apice).
     */
    protected static String escapeAttr(String s) {
        StringBuilder out = new StringBuilder((int) (s.length() * 1.2));
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '&':
                    out.append("&amp;");
                    break;
                case '<':
                    out.append("&lt;");
                    break;
                case '"':
                    out.append("&quot;");
                    break;
                case '>':
                    out.append("&gt;");
                    break;
                default:
                    out.append(c);
            }
        }
        return out.toString();
    }
}
