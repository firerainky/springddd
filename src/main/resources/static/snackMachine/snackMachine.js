const rootURI = "http://localhost:23333/snackmachines/1";

getSnackMachine();

function getSnackMachine() {
  $.get(rootURI, function (data, status) {
    $("#moneyInserted").html(data.moneyInTransaction.amount);
    $("#cent").html(data.moneyInside.oneCentCount);
    $("#tenCent").html(data.moneyInside.tenCentCount);
    $("#quarter").html(data.moneyInside.quarterCount);
    $("#dollar").html(data.moneyInside.oneDollarCount);
    $("#fiveDollar").html(data.moneyInside.fiveDollarCount);
    $("#twentyDollar").html(data.moneyInside.twentyDollarCount);
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
