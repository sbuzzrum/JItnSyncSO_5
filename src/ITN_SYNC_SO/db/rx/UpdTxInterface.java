/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ITN_SYNC_SO.db.rx;

import ITN_SYNC_SO.Synch;

/**
 *
 * @author lucio
 */
public interface UpdTxInterface {
    public boolean excecuteUpdate(Synch synch);
    public boolean getNewConnection();
    public void shutdown();
}
