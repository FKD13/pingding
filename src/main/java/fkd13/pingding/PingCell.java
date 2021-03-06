package fkd13.pingding;

import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;

public class PingCell extends TableCell<PingThing, String> {

    private MainCompanion mainCompanion;

    public PingCell(MainCompanion mainCompanion) {
        this.mainCompanion = mainCompanion;
    }

    @Override
    protected void updateItem(String s, boolean b) {
        super.updateItem(s + " ms", b);

        ObservableList<String> rowStyle = getTableRow().getStyleClass();

        rowStyle.removeAll("offline", "good", "bad", "error");
        if (!isEmpty()) {
            if (!s.equals("-")) {
                if (s.matches("^[0-9]+$")) {
                    int ping = Integer.parseInt(s);
                    if (ping < mainCompanion.getGoodPingLimit()) {
                        rowStyle.add("good");
                    } else {
                        rowStyle.add("bad");
                    }
                    setText(s + " ms");
                } else {
                    if (s.equalsIgnoreCase("Offline")) {
                        rowStyle.add("offline");
                    } else {
                        rowStyle.add("error");
                    }
                    setText(s);
                }
            } else {
                setText(s);
            }


        }
    }
}
