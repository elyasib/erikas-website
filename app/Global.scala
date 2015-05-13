import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import play.api.mvc.WithFilters
import com.mohiva.play.htmlcompressor.HTMLCompressorFilter
import play.filters.gzip.GzipFilter
import scala.concurrent.Future

object Global extends WithFilters(new GzipFilter, HTMLCompressorFilter()) with GlobalSettings {
	
	//private def getSubdomain (request: RequestHeader) = request.domain.replaceFirst("[\\.]?[^\\.]+[\\.][^\\.]+$", "")
	
	//override def onRouteRequest (request: RequestHeader) = getSubdomain(request) match {
	//	case "admin" => admin.Routes.routes.lift(request)
	//	case _ => web.Routes.routes.lift(request)
	//}
	
    override def onStart(app: Application) {
      Logger.info("Application has started")
    }
	
    override def onStop(app: Application) {
      Logger.info("Application shutdown")
    }

	//// 404 - page not found error
	//override def onHandlerNotFound (request: RequestHeader) = getSubdomain(request) match {
	//	case "admin" => GlobalAdmin.onHandlerNotFound(request)
	//	case _ => GlobalWeb.onHandlerNotFound(request)
	//}
	//
	//// 500 - internal server error
	//override def onError (request: RequestHeader, throwable: Throwable) = getSubdomain(request) match {
	//	case "admin" => GlobalAdmin.onError(request, throwable)
	//	case _ => GlobalWeb.onError(request, throwable)
	//}
	//
	//// called when a route is found, but it was not possible to bind the request parameters
	//override def onBadRequest (request: RequestHeader, error: String) = getSubdomain(request) match {
	//	case "admin" => GlobalAdmin.onBadRequest(request, error)
	//	case _ => GlobalWeb.onBadRequest(request, error)
	//}

}
