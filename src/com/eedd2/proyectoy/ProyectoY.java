package com.eedd2.proyectoy;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

import org.jgrapht.DirectedGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.AbstractGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.eedd2.proyectoy.graph.ProyectoYEdge;
import com.eedd2.proyectoy.graph.ProyectoYGraph;
import com.eedd2.proyectoy.model.Pelicula;

public class ProyectoY {

	private static String fileName = "html/index.html";
	private static String saveFileName;

	public static ProyectoYGraph<Pelicula, ProyectoYEdge> cargar() {
		ProyectoYGraph<Pelicula, ProyectoYEdge> orbe = new ProyectoYGraph<Pelicula, ProyectoYEdge>(ProyectoYEdge.class);

		File file = new File("peliculas.y");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String linea;
			String[] datos;
			Pelicula temporal;
			while ((linea = br.readLine()) != null) {
				datos = linea.split("\\|");
				temporal = new Pelicula();
				temporal.setId(Integer.parseInt(datos[0]));
				temporal.setNombre(datos[1]);
				temporal.setAnio(datos[2]);
				temporal.setDirector(datos[3]);
				temporal.setProtagonista(datos[4]);
				temporal.setGenero(datos[5]);
				temporal.setComprar(datos[7]);
				String[] trailerarray = datos[8].split(" ");
				temporal.setTrailers(Arrays.asList(trailerarray)
						.stream()
						.filter(trailer -> !trailer.equals("N/A"))
						.collect(Collectors.toList()));
				temporal.setImagen(datos[9].equals("N/A") ? "" : datos[9]);
				orbe.addVertex(temporal);
			}
			orbe.vertexSet()
			.stream()
			.forEach(pelicula -> {
				orbe.vertexSet()
				.stream()
				.filter(tmp -> !tmp.equals(pelicula))
				.forEach(pelicula2 -> {
					if (!pelicula.equals(pelicula2)) {
						ProyectoYEdge edge;
						if (pelicula.getProtagonista().equals(pelicula2.getProtagonista())) {
							edge = new ProyectoYEdge(RelType.PROTAGONISTA);
							if (!orbe.containsEdge(edge))
								orbe.addEdge(pelicula, pelicula2, edge);
						}
						if (pelicula.getDirector().equals(pelicula2.getDirector())) {
							edge = new ProyectoYEdge(RelType.DIRECTOR);
							if (!orbe.containsEdge(edge))
								orbe.addEdge(pelicula, pelicula2, edge);
						}
					}
				});
			});
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("grafo de "+ orbe.vertexSet().size()+" nodos y "+orbe.edgeSet().size()+" enlaces.");
		return orbe;
	}

	public static void mostrar(AbstractGraph<Pelicula, ProyectoYEdge> grafo) throws IOException, URISyntaxException {
		System.out.println("Mostrando grafo de "+grafo.vertexSet().size()+" nodos y "+grafo.edgeSet().size()+" enlaces.");
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>\r\n" + "<meta charset=\"utf-8\">\r\n<style>\r\n"
				+ "  body,\r\n  html {\r\n    height: 100%;\r\n    width: 100%;\r\n  }\r\n\r\n  .node {\r\n    stroke-width: 1.5px;\r\n  }\r\n\r\n  .node text {\r\n    font-size: 10px;\r\n  }\r\n\r\n  .link {\r\n    stroke: #000;\r\n    stroke-width: 1px;\r\n  }\r\n\r\n  #graph {\r\n    position: fixed !important;\r\n  }\r\n\r\n  .white {\r\n    width: 100%;\r\n    height: 90%;\r\n    box-sizing: border-box;\r\n  }\r\n\r\n  .panel {\r\n    height: 600px;\r\n    overflow: auto;\r\n  }\r\n\r\n  .trailers iframe {\r\n    margin-right: 10px;\r\n  }\r\n\r\n  #poster {\r\n    max-width: 100%;\r\n  }\r\n</style>\r\n<html>\r\n\r\n<head>\r\n  <title>Proyecto Y</title>\r\n  <link rel=\"stylesheet\" href=\"http://neo4j-contrib.github.io/developer-resources/language-guides/assets/css/main.css\">\r\n  <script src=\"js/d3.min.js\"></script>\r\n  <script src=\"https://code.jquery.com/jquery-2.2.0.min.js\"></script>\r\n  <script src=\"js/angular.min.js\"></script>\r\n</head>\r\n\r\n<body ng-app=\"proyecto-y\" ng-controller=\"mainController as main\">\r\n  <div id=\"graph\">\r\n  </div>\r\n  <div role=\"navigation\" class=\"navbar navbar-default navbar-static-top\">\r\n    <div class=\"container\">\r\n      <div class=\"row\">\r\n        <!-- <div class=\"col-sm-6 col-md-6\">\r\n          <ul class=\"nav navbar-nav\">\r\n            <li>\r\n              <form role=\"search\" class=\"navbar-form\" id=\"search\">\r\n                <div class=\"form-group\">\r\n                  <input type=\"text\" value=\"Matrix\" placeholder=\"Search for Pelicula Title\" class=\"form-control\" name=\"search\">\r\n                </div>\r\n                <button class=\"btn btn-default\" type=\"submit\">Search</button>\r\n              </form>\r\n            </li>\r\n          </ul>\r\n        </div> -->\r\n        <div class=\"navbar-header col-sm-6 col-md-6\">\r\n          <div class=\"logo-well\">\r\n            <a href=\"#\">\r\n              <img src=\"https://www.ycombinator.com/images/ycombinator-logo-fb889e2e.png\" alt=\":v\" id=\"logo\">\r\n            </a>\r\n          </div>\r\n          <div class=\"navbar-brand\">\r\n            <div class=\"brand\">Proyecto Y</div>\r\n          </div>\r\n        </div>\r\n      </div>\r\n    </div>\r\n  </div>\r\n\r\n  <div class=\"row\">\r\n    <div class=\"col-md-4\">\r\n      <div class=\"panel panel-default\">\r\n        <div class=\"panel-heading\">Resultados de busqueda <span class=\"pull-right\" id=\"close\" ng-click=\"showGraph()\"><strong>X</strong></span></div>\r\n        <table id=\"results\" class=\"table table-striped table-hover\">\r\n          <thead>\r\n            <tr>\r\n              <th>Pelicula</th>\r\n              <th>Lanzamiento</th>\r\n              <th>Vistas</th>\r\n            </tr>\r\n          </thead>\r\n          <tbody>\r\n            <tr ng-repeat=\"pelicula in resultados\" ng-click=\"update(pelicula)\">\r\n              <td>{{pelicula.nombre}}</td>\r\n              <td>{{pelicula.anio}}</td>\r\n              <td>{{pelicula.rating}}</td>\r\n            </tr>\r\n          </tbody>\r\n        </table>\r\n      </div>\r\n    </div>\r\n    <div class=\"col-md-8\">\r\n      <div class=\"panel panel-default\" ng-show=\"peliculaActiva\">\r\n        <div class=\"panel-heading\" id=\"title\">Detalle <span class=\"pull-right\" id=\"close\" ng-click=\"cleanActive()\"><strong>X</strong></span></div>\r\n        <div class=\"row\">\r\n          <div class=\"col-sm-3 col-md-3\">\r\n            <img src=\"\" ng-src=\"{{peliculaActiva.imagen}}\" ng-show=\"peliculaActiva.imagen\" class=\"well\" id=\"poster\" />\r\n          </div>\r\n          <div class=\"col-md-9 col-sm-9\">\r\n            <h2>{{peliculaActiva.nombre}}</h2>\r\n            <h3 class=\"pull-right\">{{peliculaActiva.anio}}</h3>\r\n            <ul id=\"crew\">\r\n              <li>{{peliculaActiva.director}}</li>\r\n              <li>{{peliculaActiva.protagonista}}</li>\r\n            </ul>\r\n            <a ng-href=\"{{peliculaActiva.comprar}}\" class=\"pull-right\" ng-show=\"peliculaActiva.comprar\"><img src=\"img/amazon.gif\" width=\"120px\" height=\"42px\" /></a>\r\n            <div class=\"trailers\">\r\n              <iframe width=\"270\" height=\"215\" ng-src=\"{{trailer}}\" frameborder=\"0\" ng-repeat=\"trailer in peliculaActiva.trailers track by $index\" allowfullscreen></iframe>\r\n            </div>\r\n          </div>\r\n        </div>\r\n      </div>\r\n    </div>\r\n  </div>\r\n  <div class=\"white\"></div>\r\n  <script>\r\n    var graph = {\r\n      \"nodes\":");
		sb.append(grafo.vertexSet());
		sb.append(",\"links\":");
		sb.append(grafo.edgeSet());
		sb.append("    };\r\n    var edges = [];\r\n    graph.links.forEach(function(e) {\r\n      var sourceNode = graph.nodes.filter(function(n) {\r\n          return n.id === e.source;\r\n        })[0],\r\n        targetNode = graph.nodes.filter(function(n) {\r\n          return n.id === e.target;\r\n        })[0];\r\n      edges.push({\r\n        source: sourceNode,\r\n        target: targetNode,\r\n        value: e.type\r\n      });\r\n    });\r\n\r\n    var force = d3.layout.force()\r\n      .size([1000, 1000])\r\n      .charge(-400)\r\n      .linkDistance(function(d) {\r\n        if (d.type == \"DIRECTOR\")\r\n          return 100;\r\n        return 400;\r\n      })\r\n      .on(\"tick\", tick);\r\n\r\n    var drag = force.drag()\r\n      .on(\"dragstart\", dragstart)\r\n      .on(\"dragend\", dragended);\r\n\r\n    var svg = d3.select(\"#graph\").append(\"svg\")\r\n      .attr(\"width\", \"100%\").attr(\"height\", \"100%\")\r\n      .attr(\"pointer-events\", \"all\");\r\n\r\n    var link = svg.selectAll(\".link\"),\r\n      node = svg.selectAll(\".node\");\r\n\r\n\r\n    var defs = svg.append(\"defs\");\r\n    var filter = defs.append(\"filter\")\r\n      .attr(\"id\", \"drop-shadow\")\r\n      .attr(\"height\", \"130%\");\r\n\r\n    // SourceAlpha refers to opacity of graphic that this filter will be applied to\r\n    // convolve that with a Gaussian with standard deviation 3 and store result\r\n    // in blur\r\n    filter.append(\"feGaussianBlur\")\r\n      .attr(\"in\", \"SourceAlpha\")\r\n      .attr(\"stdDeviation\", 5)\r\n      .attr(\"result\", \"blur\");\r\n\r\n    // translate output of Gaussian blur to the right and downwards with 2px \r\n    // store result in offsetBlur\r\n    filter.append(\"feOffset\")\r\n      .attr(\"in\", \"blur\")\r\n      .attr(\"dx\", 2)\r\n      .attr(\"dy\", 2)\r\n      .attr(\"result\", \"offsetBlur\");\r\n\r\n    // overlay original SourceGraphic over translated blurred opacity by using\r\n    // feMerge filter. Order of specifying inputs is important!\r\n    var feMerge = filter.append(\"feMerge\");\r\n\r\n    feMerge.append(\"feMergeNode\")\r\n      .attr(\"in\", \"offsetBlur\")\r\n    feMerge.append(\"feMergeNode\")\r\n      .attr(\"in\", \"SourceGraphic\");\r\n\r\n    force\r\n      .nodes(graph.nodes)\r\n      .links(edges)\r\n      .start();\r\n\r\n    link = link.data(edges)\r\n      .enter().append(\"line\")\r\n      .attr(\"class\", \"link\")\r\n      .style(\"stroke\", function(d) {\r\n        switch (d.value) {\r\n          case \"DIRECTOR\":\r\n            return \"rgb(31, 157, 180)\";\r\n            break;\r\n          case \"PROTAGONISTA\":\r\n            return \"rgb(189, 201, 41)\";\r\n            break;\r\n          default:\r\n            return \"rgb(0, 0, 0)\";\r\n            break;\r\n        }\r\n      });\r\n\r\n\r\n    node = node.data(graph.nodes)\r\n      .enter().append(\"g\")\r\n      .attr(\"class\", \"node\")\r\n      .on(\"dblclick\", dblclick)\r\n      .attr(\"ng-click\", function(d) {\r\n        return \"updateFromGraph(\" + d.id + \")\"\r\n      })\r\n      .call(drag);\r\n    node.append(\"svg:rect\")\r\n      .attr(\"width\", function(d) {\r\n        return d.nombre.length * 5 + 60\r\n      })\r\n      .attr(\"x\", \"-20px\")\r\n      .attr(\"y\", \"-23px\")\r\n      .style(\"filter\", \"url(#drop-shadow)\")\r\n      .attr(\"height\", \"45px\")\r\n      .style(\"fill\", function(d){ return d.color ? d.color : \"rgb(27, 172, 159)\"});\r\n\r\n    node.append(\"svg:image\")\r\n      .attr(\"class\", \"circle\")\r\n      .attr(\"xlink:href\", function(d) {\r\n        return d.imagen\r\n      })\r\n      .attr(\"x\", \"-18px\")\r\n      .attr(\"y\", \"-18px\")\r\n      .attr(\"width\", \"36px\")\r\n      .attr(\"height\", \"36px\");\r\n\r\n    node.append(\"text\")\r\n      .attr(\"dx\", 18)\r\n      .attr(\"dy\", \".25em\")\r\n      .style(\"fill\", \"rgb(247, 247, 247)\")\r\n      .text(function(d) {\r\n        return d.nombre\r\n      });\r\n\r\n    function tick() {\r\n      link.attr(\"x1\", function(d) {\r\n          return d.source.x;\r\n        })\r\n        .attr(\"y1\", function(d) {\r\n          return d.source.y;\r\n        })\r\n        .attr(\"x2\", function(d) {\r\n          return d.target.x;\r\n        })\r\n        .attr(\"y2\", function(d) {\r\n          return d.target.y;\r\n        });\r\n\r\n\r\n      node.attr(\"transform\", function(d) {\r\n        return \"translate(\" + d.x + \",\" + d.y + \")\";\r\n      });\r\n\r\n    }\r\n\r\n    resize();\r\n    d3.select(window).on(\"resize\", resize);\r\n\r\n    function resize() {\r\n      width = window.innerWidth, height = window.innerHeight;\r\n      svg.attr(\"width\", width).attr(\"height\", height);\r\n      force.size([width + 30, height]).resume();\r\n    }\r\n\r\n    function dblclick(d) {\r\n      d3.select(this).classed(\"fixed\", d.fixed = false);\r\n\r\n    }\r\n\r\n    function dragstart(d) {\r\n      d3.select(this).classed(\"fixed\", d.fixed = true);\r\n    }\r\n\r\n    function dragended(d) {\r\n      $(\'#close\').click();\r\n    }\r\n    var app = angular.module(\'proyecto-y\', []).config(function($sceProvider) {\r\n      $sceProvider.enabled(false);\r\n    });\r\n\r\n    app.controller(\'mainController\', function($scope) {\r\n      $scope.resultados = graph.nodes;\r\n      $scope.update = function(pelicula) {\r\n        $scope.peliculaActiva = pelicula;\r\n        $(\"html, body\").animate({\r\n          scrollTop: \"0px\"\r\n        });\r\n      };\r\n      $scope.updateFromGraph = function(id) {\r\n        indexRes = $scope.resultados.map(function(pelicula) {\r\n          return pelicula.id;\r\n        }).indexOf(id);\r\n        $scope.peliculaActiva = $scope.resultados[indexRes];\r\n        $(\"html, body\").animate({\r\n          scrollTop: \"0px\"\r\n        });\r\n      }\r\n\r\n      $scope.showGraph = function() {\r\n        $(\"html, body\").animate({\r\n          scrollTop: $(document).height()\r\n        }, 1000);\r\n      }\r\n      $scope.cleanActive = function() {\r\n        $scope.peliculaActiva = null;\r\n      }\r\n    });\r\n  </script>\r\n</body>\r\n\r\n</html>");
		FileWriter fw;
		BufferedWriter bw;
		File file = new File(fileName);
		fw = new FileWriter(file);
		bw = new BufferedWriter(fw);
		bw.write(sb.toString());
		bw.close();
		fw.close();
		Desktop.getDesktop().open(file);
	}

	public static void guardar(UndirectedGraph<Pelicula,ProyectoYEdge> grafo){
		File file = new File(saveFileName);
		FileWriter fw;
		BufferedWriter bw;
		try {
		fw = new FileWriter(file);
		bw = new BufferedWriter(fw);
		grafo.vertexSet().forEach(pelicula ->{
				try {
					bw.write(pelicula.paraGuardar()+"\n");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		});
		bw.close();
		fw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static enum RelType {
		DIRECTOR, PROTAGONISTA;
	}
}
