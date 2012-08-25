package cz.cvut.fel.x36tjv.javafx;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.io.http.HttpRequest;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.math.BigDecimal;
import javafx.scene.layout.Tile;
import javafx.geometry.HPos;

var fromCurrencyField = TextBox {
            text: ""
            columns: 12
            selectOnFocus: true
        };
var fromAmountField = TextBox {
            text: ""
            columns: 12
            selectOnFocus: true
        };
var toCurrencyField = TextBox {
            text: ""
            columns: 12
            selectOnFocus: true
        };
var toAmountLabel = Label {
            width: 100
        };
var msg = "";
var msgLabel = Label {
            width: 50
            text: bind msg
        };

Stage {
    title: "Converter"

    width: 300
    height: 200
    x: 400
    y: 400
    resizable: true
    scene: Scene {
        width: 300
        height: 200
        content: [
            Tile {
                columns: 2
                rows: 5
                hgap: 10
                vgap: 10
                nodeHPos: HPos.CENTER
                content: [
                    Label {
                        text: "From currency:"
                    }
                    fromCurrencyField,
                    Label {
                        text: "Amount:"
                    }
                    fromAmountField,
                    Label {
                        text: "To currency:"
                    }
                    toCurrencyField,
                    Label {
                        text: "Amount:"
                    }
                    toAmountLabel,
                    msgLabel,
                    Button {
                        text: "Convert"
                        action: function () {
                            var req = HttpRequest {
                                        location: "http://localhost:8080/X36TJVExchangeOfficeWS/resources/ExchangeRate/exchangerate/{fromCurrencyField.text}{toCurrencyField.text}",
                                        method: "GET",
                                        onError: function (is: InputStream) {
                                            msg = "An error occured."
                                        },
                                        onInput: function (is: InputStream) {
                                            var reader = new BufferedReader(new InputStreamReader(is));
                                            var rate: BigDecimal = new BigDecimal(reader.readLine());
                                            var amount: BigDecimal = new BigDecimal(fromAmountField.text);
                                            toAmountLabel.text = amount.multiply(rate).toString();
                                            msg = "";
                                        }
                                    };
                            req.start();
                        }
                    }
                ]
            }
        ]
    }
}
