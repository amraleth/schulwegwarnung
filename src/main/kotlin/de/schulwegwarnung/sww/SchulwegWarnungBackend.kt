package de.schulwegwarnung.sww

import de.schulwegwarnung.sww.controller.CityController
import de.schulwegwarnung.sww.controller.ImageController
import de.schulwegwarnung.sww.controller.MarkerController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Configures and launches the backend
 *
 * @author amraleth
 */
class SchulwegWarnungBackend {
    private val version: String = "1"
    private val logger: Logger = LoggerFactory.getLogger("SchulwegWarnung")

    private lateinit var javalin: Javalin

    private val markerController: MarkerController = MarkerController()
    private val cityController: CityController = CityController()
    private val imageController: ImageController = ImageController()

    /**
     * Starts the backend
     */
    fun startBackend() {
        this.logger.info("Starting backend version $version ...")

        this.javalin = Javalin.create { config ->
            config.router.apiBuilder {
                path("/api/v$version") {
                    path("/marker") {
                        get(this.markerController::getAllMarkers)
                        post(this.markerController::createMarker)
                        put(this.markerController::modifyMarker)
                        delete(this.markerController::deleteMarker)
                    }
                    path("/city") {
                        get(this.cityController::GetCities)
                    }
                    path("/image") {
                        get(this.imageController::retrieveImage)
                        post(this.imageController::createImage)
                    }
                }
            }
        }.start(8080)
    }
}