// register our custom symbols to nvd3
// make sure your path is valid given any size because size scales if the chart scales.
nv.utils.symbolMap.set('thin-x', function(size) {
    size = Math.sqrt(size);
    return 'M' + (-size/2) + ',' + (-size/2) +
            'l' + size + ',' + size +
            'm0,' + -(size) +
            'l' + (-size) + ',' + size;
});



//var $svg = $('#graph svg');
//$svg.parent().append('<div class="col-lg-6">Ceci est un test 2</div>');

function loadJSON(callback, filePath) {
  var xobj = new XMLHttpRequest();
  xobj.overrideMimeType("application/json");
  xobj.open('GET', filePath, false);
  //xobj.open('GET', 'json/matrice.json', false); // Replace 'my_data' with the path to your file
  xobj.onreadystatechange = function () {
        if (xobj.readyState == 4 && xobj.status == "200") {
          // Required use of an anonymous callback as .open will NOT return a value but simply returns undefined in asynchronous mode
          callback(xobj.responseText);
        }
  };
  xobj.send(null);
}
// create the chart
var dataParam;
var chart;

nv.addGraph(function() {
    chart = nv.models.scatterChart()
        .showDistX(true)
        .showDistY(true)
        .useVoronoi(true)
        .color(d3.scale.category10().range())
        .duration(300)
        .showLegend(false)
    ;

    chart.dispatch.on('renderEnd', function(){
        console.log('render complete');
    });

    chart.xAxis.tickFormat(d3.format('.02f'));
    chart.yAxis.tickFormat(d3.format('.02f'));

    console.log("processing graph parameters ");
    loadJSON(function(response) {

        var obj = JSON.parse(response)
        //console.log(obj);
        chart.xAxis.axisLabel(obj.labelx);
        chart.yAxis.axisLabel(obj.labely);
        var sp1 = document.createElement("h2");
        // ajout du titre du gaph dans fichier html
        // crée un peu de contenu pour cet élément.
        var sp1_content = document.createTextNode(obj.titre);
        // ajoute ce contenu au nouvel élément
        sp1.appendChild(sp1_content);
        // lui donne un attribut id appelé 'nouveauSpan'
        var sp2 = document.getElementById("graphtitle");
        // Obtient une référence du nœud parent
        var parentDiv = sp2.parentNode;
        // insère le nouvel élément dans le DOM avant sp2
        parentDiv.insertBefore(sp1, sp2);
      },'json/param.json');

    console.log("processing DATA and plot:: ");

    loadJSON(function(response) {

     d3.select('#graph svg')
         //.datum(dataa)
         .datum(JSON.parse(response))
         .call(chart);
     },'json/nvd.json');

    //randomData(1,20);
    //console.log(JSON.stringify(dataa));
    //console.table(dataa);
    //console.log(data1);
    //console.table(data1);
    nv.utils.windowResize(chart.update);
    chart.dispatch.on('stateChange', function(e) { ('New State:', JSON.stringify(e)); });
    return chart;
});
//
/*   function randomData(groups, points) { //# groups,# points per group
    // smiley and thin-x are our custom symbols!
  //    var data = [],
         shapes = ['thin-x', 'circle', 'cross', 'triangle-up', 'triangle-down', 'diamond', 'square'],
          random = d3.random.normal();

        for (i = 0; i < groups; i++) {
          data.push({
            key: 'Group ' + i,
            values: []
        });

        for (j = 0; j < points; j++) {
            data[i].values.push({
                x: random(),
                y: random(),
              size: Math.round(Math.random() * 100) / 100,
                shape: shapes[j % shapes.length]
            });
        }
    }

    return data;
}*/
