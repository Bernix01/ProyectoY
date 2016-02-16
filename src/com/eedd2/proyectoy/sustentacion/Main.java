/**
 *
 */
package com.eedd2.proyectoy.sustentacion;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.jgrapht.graph.AbstractGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import com.eedd2.proyectoy.graph.ProyectoYEdge;
import com.eedd2.proyectoy.model.Pelicula;

/**
 * @author gbern
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DirectedWeightedMultigraph<Usuario, BasicEdge> red = MrSustentador.cargar();
		try {
			mostrar(red);
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public static void mostrar(AbstractGraph<Usuario, BasicEdge> grafo) throws IOException, URISyntaxException {




		System.out.println(
				"Mostrando grafo de " + grafo.vertexSet().size() + " nodos y " + grafo.edgeSet().size() + " enlaces.");
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>\r\n<meta charset=\"utf-8\">\r\n<style>\r\n  body,\r\n  html {\r\n    height: 100%;\r\n    width: 100%;\r\n  }\r\n\r\n  .node {\r\n    stroke-width: 1.5px;\r\n  }\r\n\r\n  .node text {\r\n    font-size: 10px;\r\n  }\r\n\r\n  .link {\r\n    stroke: #000;\r\n    stroke-width: 0.5px;\r\n  }\r\n\r\n  #graph {\r\n    position: fixed !important;\r\n  }\r\n\r\n  .white {\r\n    width: 100%;\r\n    height: 90%;\r\n    box-sizing: border-box;\r\n  }\r\n\r\n  .panel {\r\n    height: 600px;\r\n    overflow: auto;\r\n  }\r\n\r\n  .trailers iframe {\r\n    margin-right: 10px;\r\n  }\r\n\r\n  #poster {\r\n    max-width: 100%;\r\n  }\r\n\r\n</style>\r\n<html>\r\n\r\n  <head>\r\n    <title>Proyecto Y</title>\r\n    <link rel=\"stylesheet\" href=\"http://neo4j-contrib.github.io/developer-resources/language-guides/assets/css/main.css\">\r\n    <script src=\"js/d3.js\"></script>\r\n    <script src=\"https://code.jquery.com/jquery-2.2.0.min.js\"></script>\r\n  </head>\r\n\r\n  <body ng-app=\"proyecto-y\" ng-controller=\"mainController as main\">\r\n    <div id=\"graph\"></div>\r\n    <div role=\"navigation\" class=\"navbar navbar-default navbar-static-top\">\r\n      <div class=\"container\">\r\n        <div class=\"row\">\r\n          <!-- <div class=\"col-sm-6 col-md-6\">\r\n          <ul class=\"nav navbar-nav\">\r\n            <li>\r\n              <form role=\"search\" class=\"navbar-form\" id=\"search\">\r\n                <div class=\"form-group\">\r\n                  <input type=\"text\" value=\"Matrix\" placeholder=\"Search for Pelicula Title\" class=\"form-control\" name=\"search\">\r\n                </div>\r\n                <button class=\"btn btn-default\" type=\"submit\">Search</button>\r\n              </form>\r\n            </li>\r\n          </ul>\r\n        </div> -->\r\n          <div class=\"navbar-header col-sm-6 col-md-6\">\r\n            <div class=\"logo-well\">\r\n              <a href=\"#\">\r\n                <img src=\"https://www.ycombinator.com/images/ycombinator-logo-fb889e2e.png\" alt=\":v\" id=\"logo\">\r\n              </a>\r\n            </div>\r\n            <div class=\"navbar-brand\">\r\n              <div class=\"brand\">Proyecto Y</div>\r\n            </div>\r\n          </div>\r\n        </div>\r\n      </div>\r\n    </div>\r\n    <script>\r\n      var graph = { nodes:");
		sb.append(grafo.vertexSet());
		sb.append(",\"links\":");
		sb.append(grafo.edgeSet());
		sb.append("};\r\n    var edges = [];\r\n\r\n      graph.links.forEach(function(e) {\r\n        var sourceNode = graph.nodes.filter(function(n) {\r\n            return n.name === e.source;\r\n          })[0],\r\n          targetNode = graph.nodes.filter(function(n) {\r\n            return n.name === e.target;\r\n          })[0];\r\n        edges.push({\r\n          source: sourceNode,\r\n          target: targetNode,\r\n          value: e.distancia\r\n        });\r\n      });\r\n\r\n      function getRandomInt(min, max) {\r\n        return Math.floor(Math.random() * (max - min + 1) + min);\r\n      }\r\n\r\n      var force = d3.layout.force().size([1000, 1000]).charge(-400).linkDistance(400).on(\"tick\", tick);\r\n\r\n      var drag = force.drag().on(\"dragstart\", dragstart).on(\"dragend\", dragended);\r\n\r\n      var svg = d3.select(\"#graph\").append(\"svg\").attr(\"width\", \"100%\").attr(\"height\", \"100%\").attr(\"pointer-events\", \"all\");\r\n\r\n      var link = svg.selectAll(\".link\"),\r\n        node = svg.selectAll(\".node\");\r\n      force.nodes(graph.nodes).links(edges).start();\r\n\r\n      link = link.data(edges).enter().append(\"line\").attr(\"class\", \"link\");\r\n\r\n      node = node.data(graph.nodes).enter().append(\"g\").attr(\"class\", \"node\").on(\"dblclick\", dblclick).call(drag);\r\n      node.append(\"svg:rect\").attr(\"width\", \"36px\").attr(\"x\", \"-20px\").attr(\"y\", \"-23px\").style(\"filter\", \"url(#drop-shadow)\").attr(\"height\", \"45px\").style(\"fill\", function(d) {\r\n        return \"rgb(27, 172, 159)\";\r\n      });\r\n\r\n          node.append(\"svg:rect\")\r\n            .attr(\"class\", \"circle\")\r\n            .attr(\"x\", \"-18px\")\r\n            .attr(\"y\", \"-18px\")\r\n            .attr(\"width\", \"36px\")\r\n            .attr(\"height\", \"36px\")\r\n            .style(\"fill\",\"blue\");\r\n            link.append(\"text\")\r\n          .attr(\"font-family\", \"Arial, Helvetica, sans-serif\")\r\n          .attr(\"fill\", \"Black\")\r\n          .style(\"font\", \"normal 12px Arial\")\r\n          .attr(\"transform\", function(d) {\r\n              return \"translate(\" +\r\n                  ((d.source.y + d.target.y)/2) + \",\" +\r\n                  ((d.source.x + d.target.x)/2) + \")\";\r\n          })\r\n          .attr(\"dy\", \".35em\")\r\n          .attr(\"text-anchor\", \"middle\")\r\n          .text(function(d) {\r\n              return d.value;\r\n          });\r\n\r\n      node.append(\"text\").attr(\"dx\", 18).attr(\"dy\", \".25em\").style(\"fill\", \"rgb(144, 144, 144)\").text(function(d) {\r\n        return d.name;\r\n      });\r\n\r\n      function tick() {\r\n        link.attr(\"x1\", function(d) {\r\n          return d.source.x;\r\n        }).attr(\"y1\", function(d) {\r\n          return d.source.y;\r\n        }).attr(\"x2\", function(d) {\r\n          return d.target.x;\r\n        }).attr(\"y2\", function(d) {\r\n          return d.target.y;\r\n        });\r\n\r\n        node.attr(\"transform\", function(d) {\r\n          return \"translate(\" + d.x + \",\" + d.y + \")\";\r\n        });\r\n\r\n      }\r\n\r\n      resize();\r\n      d3.select(window).on(\"resize\", resize);\r\n\r\n      function resize() {\r\n        width = window.innerWidth,\r\n        height = window.innerHeight;\r\n        svg.attr(\"width\", width).attr(\"height\", height);\r\n        force.size([\r\n          width + 30,\r\n          height\r\n        ]).resume();\r\n      }\r\n\r\n      function dblclick(d) {\r\n        d3.select(this).classed(\"fixed\", d.fixed = false);\r\n\r\n      }\r\n\r\n      function dragstart(d) {\r\n        d3.select(this).classed(\"fixed\", d.fixed = true);\r\n      }\r\n\r\n      function dragended(d) {\r\n        $(\'#close\').click();\r\n      }\r\n    </script>\r\n  </body>\r\n\r\n</html>\r\n");
		FileWriter fw;
		BufferedWriter bw;
		String fileName = "html/indexc.html";
		File file = new File(fileName);
		fw = new FileWriter(file);
		bw = new BufferedWriter(fw);
		bw.write(sb.toString());
		bw.close();
		fw.close();
		Desktop.getDesktop().open(file);
	}

}
