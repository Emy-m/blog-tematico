package web;

import java.util.Map;

import api.PaginaService;
import api.PublicacionService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class WebAPI {
    private int webPort;
    private PublicacionService publicacionService;
    private PaginaService paginaService;

    public WebAPI(PublicacionService publicacionService, PaginaService paginaService, int webPort) {
        this.publicacionService = publicacionService;
        this.paginaService = paginaService;
        this.webPort = webPort;
    }

    public void start() {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
        }).start(this.webPort);

        app.get("/pages/:id", obtenerPagina());
        app.get("/posts/latest", obtenerUltimosPosts());
        app.get("/posts/:id", obtenerPost());
        app.get("/posts/author/:name", obtenerPostsAutor());
        app.get("/byauthor", obtenerAutoresCantidadPosts());
        app.get("/search/:text", busquedaPorTexto());

        app.exception(Exception.class, (e, ctx) -> {
            ctx.json(Map.of("result", "error", "message", "Ups... algo se rompió.: " + e.getMessage()))
                    .status(400);
        });
    }

    private Handler obtenerPagina() {
        return ctx -> {
            var paginaId = ctx.pathParam("id");
            var pagina = this.paginaService.pagina(paginaId);
            ctx.json(Map.of("result", "success", "pagina", pagina));
        };
    }

    private Handler obtenerUltimosPosts() {
        return ctx -> {
            var posts = this.publicacionService.ultimosPosts();
            ctx.json(Map.of("result", "success", "posts", posts));
        };
    }

    private Handler obtenerPost() {
        return ctx -> {
            var postId = ctx.pathParam("id");
            var post = this.publicacionService.post(postId);
            ctx.json(Map.of("result", "success", "post", post));
        };
    }

    private Handler obtenerPostsAutor() {
        return ctx -> {
            var nombre = ctx.pathParam("nombre");
            var posts = this.publicacionService.posts(nombre);
            ctx.json(Map.of("result", "success", "posts", posts));
        };
    }

    private Handler obtenerAutoresCantidadPosts() {
        return ctx -> {
            var posts = this.publicacionService.autoresPosts();
            ctx.json(Map.of("result", "success", "posts", posts));
        };
    }

    private Handler busquedaPorTexto() {
        return ctx -> {
            var texto = ctx.pathParam("text");
            var posts = this.publicacionService.postsPorTexto(texto);
            ctx.json(Map.of("result", "success", "posts", posts));
        };
    }
}
