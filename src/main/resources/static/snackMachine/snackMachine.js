const rootURI = "http://localhost:23333/snackmachines/1";

getSnackMachine();

function getSnackMachine() {
  $.get(rootURI, function (data, status) {
    $("#moneyInserted").html(data.moneyInTransaction);
    $("#cent").html(data.oneCentCount);
    $("#tenCent").html(data.tenCentCount);
    $("#quarter").html(data.quarterCount);
    $("#dollar").html(data.oneDollarCount);
    $("#fiveDollar").html(data.fiveDollarCount);
    $("#twentyDollar").html(data.twentyDollarCount);
  });
}

$("button").click(function () {
    switch (this.id) {
        case "btnInsertCent":
            insert("Cent")
            break;
        case "btnInsertTenCent":
            insert("TenCent")
            break;
        case "btnInsertQuarter":
            insert("Quarter")
            break;
        case "btnInsertDollar":
            insert("Dollar")
            break;
        case "btnInsertFiveDollar":
            insert("FiveDollar")
            break;
        case "btnInsertTwentyDollar":
            insert("TwentyDollar")
            break;
        case "btnReturnMoney":
            returnMoney()
            break;
        case "btnBuy":
            buy(1)
            break;
        default:
            break;
    }
});

function insert(coinOrNote) {
    $.ajax({
        type: "PUT",
        url: rootURI + "/moneyInTransaction/" + coinOrNote,
        success: function (result) {
        }
    });
    location.reload();
}

function returnMoney() {
    $.ajax({
        type: "PUT",
        url: rootURI + "/moneyInTransaction",
        success: function (result) {
        }
    });
    location.reload();
}

function buy(position) {
    $.ajax({
        type: "PUT",
        url: rootURI + "/" + position,
        success: function (result) {
        }
    });
    location.reload();
}
