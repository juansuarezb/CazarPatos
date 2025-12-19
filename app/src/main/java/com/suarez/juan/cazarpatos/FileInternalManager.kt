package com.suarez.juan.cazarpatos

import android.app.Activity
import android.content.Context

class FileInternalManager(val actividad: Activity) : FileHandler {

    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        val texto = datosAGrabar.first + "," + datosAGrabar.second
        actividad.openFileOutput(
            "datos.txt",
            Context.MODE_PRIVATE
        ).bufferedWriter().use { fos ->
            fos.write(texto)
        }
    }

    override fun ReadInformation(): Pair<String, String> {
        try {
            actividad.openFileInput("datos.txt").bufferedReader().use {
                val datoLeido = it.readLine()
                val textArray = datoLeido.split(",")
                val email = textArray[0]
                val clave = textArray[1]
                return email to clave
            }
        } catch (e: Exception) {
            return "" to ""
        }
    }

}
