/**
 * Librería YunoSignifyd para la integración con el servicio de detección de fraudes de Signifyd.
 *
 * Esta librería utiliza la librería ThreatMetrix para generar un perfil de dispositivo y enviarlo al servidor de Signifyd
 * para su análisis de fraude.
 */
package com.yuno.yunosignifyd

import android.app.Application
import android.content.Context
import com.lexisnexisrisk.threatmetrix.TMXConfig
import com.lexisnexisrisk.threatmetrix.TMXProfiling

private var setOrgid = "w2txo5aa"
private var setFPServer = "imgs.signifyd.com"
private var setSessionId = ""
private val config = TMXConfig()

/**
 * Inicializa la librería YunoSignifyd.
 *
 * @param context El contexto de la activity.
 */
fun Application.initYunoSignifyd() {
    getSessionId(this){ sessionId ->
        setSessionId = sessionId
    }
}

/**
 * Obtiene el ID de sesión de YunoSignifyd de manera asíncrona.
 *
 * @param context El contexto de la aplicación.
 * @param sessionId La función de devolución de llamada que se llamará con el ID de sesión obtenido.
 */

private fun getSessionId(context: Context, sessionId: (sessionId: String) -> Unit) {
    config.setOrgId(setOrgid)
    config.setFPServer(setFPServer)
    config.setContext(context)
    TMXProfiling.getInstance().init(config)

    try {
        TMXProfiling.getInstance().profile { sessionId ->
            sessionId(sessionId.sessionID)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
/**
 * Obtiene el ID de sesión de YunoSignifyd.
 *
 * @return El ID de sesión o una cadena vacía si no se ha obtenido.
 */
fun Context.onCreateYunoSignifyd(): String {
    return if (setSessionId.isEmpty()) {
        ""
    }else{
        setSessionId
    }
}

