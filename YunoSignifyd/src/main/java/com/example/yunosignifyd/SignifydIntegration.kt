/**
 * Librería YunoSignifyd para la integración con el servicio de detección de fraudes de Signifyd.
 *
 * Esta librería utiliza la librería ThreatMetrix para generar un perfil de dispositivo y enviarlo al servidor de Signifyd
 * para su análisis de fraude.
 */
package com.example.yunosignifyd

import android.app.Application
import android.content.Context
import com.lexisnexisrisk.threatmetrix.TMXConfig
import com.lexisnexisrisk.threatmetrix.TMXProfiling

private var setOrgid = ""
private var setFPServer = ""
private val config = TMXConfig()

/**
 * Inicializa la librería YunoSignifyd. Debe llamarse antes de utilizar cualquier otra función de la librería.
 *
 * @param orgId Identificador de organización proporcionado por Signifyd.
 * @param fpServer URL del servidor de detección de fraudes de Signifyd.
 */

fun Application.initYunoSignifyd(
    orgId: String,
    fpServer: String
) {
    setOrgid = orgId
    setFPServer = fpServer
}

/**
 * Función para inicializar el perfil de dispositivo con ThreatMetrix.
 *
 * Debe llamarse en el método onCreate de la actividad principal.
 *
 * @param context Contexto de la aplicación.
 */
fun Context.onCreateYunoSignifyd(context: Context) {
    config.setOrgId(setOrgid)
    config.setFPServer(setFPServer)
    config.setContext(context)
    TMXProfiling.getInstance().init(config)
}

/**
 * Genera y envía un perfil de dispositivo al servidor de detección de fraudes de Signifyd.
 *
 * Debe llamarse en el método onResume de la actividad principal.
 *
 * @param callback Función de retorno que recibe el ID de sesión generado por el servidor.
 */
fun Context.onResumeYunoSignifyd(callback: (sessionId: String) -> Unit) {
    try {
        TMXProfiling.getInstance().profile {
            callback(it.sessionID)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
