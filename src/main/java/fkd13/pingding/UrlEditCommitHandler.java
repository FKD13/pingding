package fkd13.pingding;

import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;

public class UrlEditCommitHandler implements EventHandler<TableColumn.CellEditEvent<PingThing, String>> {

    private Ponger ponger;

    public UrlEditCommitHandler(Ponger ponger) {
        this.ponger = ponger;
    }

    @Override
    public void handle(TableColumn.CellEditEvent<PingThing, String> pingThingStringCellEditEvent) {
        pingThingStringCellEditEvent.getRowValue().setUrl(pingThingStringCellEditEvent.getNewValue());
        new Thread(() -> ponger.pingUrl(pingThingStringCellEditEvent.getRowValue())).start();
    }
}
