
function addCell(x, y) {
    reloadWith( x + "|" + y)
}

function reloadWith(value) {
    var url = new URL(window.location.href);
    cells = url.searchParams.getAll("cells");
    console.log(cells)

    const index = cells.indexOf(value);
    if (index > -1) {
        console.log("remove " + value)
        cells.splice(index, 1);
    } else {
        console.log("add " + value)
        cells.push(value)
    }
    console.log(cells)
    console.log(cells.join(","))
    url.searchParams.set("cells", cells.join(","))
    window.location.href = url.href;
}

function playPause() {
    var url = new URL(window.location.href);
    tick = url.searchParams.get("tick");
    tick = tick !== "true";
    url.searchParams.set("tick", tick)
    window.location.href = url.href;
}

var url = new URL(window.location.href);
tick = url.searchParams.get("tick");
if (tick === "true") {
    setTimeout(function () {
        location.reload();
    }, 500);
}
