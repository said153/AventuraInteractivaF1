package com.f1tracker.data.repository

import com.f1tracker.R
import com.f1tracker.data.model.Auto
import com.f1tracker.data.model.Escuderia
import com.f1tracker.data.model.Piloto

class F1Repository {

    fun getEscuderias(): List<Escuderia> = listOf(
        Escuderia(1, "Mercedes", R.drawable.mercedes_logo, "#0FBF9F", "Alemania"),
        Escuderia(2, "Red Bull Racing", R.drawable.red_bull_logo, "#031627", "Austria"),
        Escuderia(3, "Ferrari", R.drawable.ferrari_logo, "#D9042B", "Italia"),
        Escuderia(4, "McLaren", R.drawable.mclaren_logo, "#F26716", "Reino Unido"),
        Escuderia(5, "Alpine", R.drawable.alpine_logo, "#011126", "Francia"),
        Escuderia(6, "Aston Martin", R.drawable.aston_martin_logo, "#035949", "Reino Unido"),
        Escuderia(7, "Williams", R.drawable.williams_logo, "#111C59", "Reino Unido"),
        Escuderia(8, "Audi", R.drawable.audi_logo, "#1F2626", "Alemania"),
        Escuderia(9, "Haas", R.drawable.haas_logo, "#591B26", "Estados Unidos"),
        Escuderia(10, "Visa Cash App RB", R.drawable.alphatauri_logo, "#020B40", "Italia"),
        Escuderia(11, "Cadillac", R.drawable.cadillac_logo, "#0D0D0D", "Estados Unidos")
    )

    fun getAutos(): List<Auto> = listOf(
        Auto(1, 1, "W17", R.drawable.mercedes_w15, "Mercedes-AMG F1 M15 E Performance"),
        Auto(2, 2, "RB22", R.drawable.red_bull_rb20, "Honda RBPT H001"),
        Auto(3, 3, "SF-26", R.drawable.ferrari_sf24, "Ferrari 066/12"),
        Auto(4, 4, "MCL40", R.drawable.mclaren_mcl38, "Mercedes-AMG F1 M15 E Performance"),
        Auto(5, 5, "A526", R.drawable.alpine_a524, "Alpine E-Tech RE24"),
        Auto(6, 6, "AMR24", R.drawable.aston_martin_amr24, "Mercedes-AMG F1 M15 E Performance"),
        Auto(7, 7, "FW46", R.drawable.williams_fw46, "Mercedes-AMG F1 M15 E Performance"),
        Auto(8, 8, "C44", R.drawable.alphatauri_at05, "Ferrari 066/12"),
        Auto(9, 9, "VF-24", R.drawable.haas_vf24, "Ferrari 066/12"),
        Auto(10, 10, "AT05", R.drawable.racing_bulls_vcarb_03, "Honda RBPT H001"),
        Auto(11, 11, "C44", R.drawable.cadillac, "Ferrari 066/12")
    )

    fun getPilotos(): List<Piloto> = listOf(
        // Mercedes (ID: 1)
        Piloto(1, 1, "Kimi Antonelli", 12, "Italiana", R.drawable.antonelli, 23),
        Piloto(2, 1, "George Russell", 63, "Británica", R.drawable.russell, 26),

        // Red Bull Racing (ID: 2)
        Piloto(3, 2, "Max Verstappen", 1, "Holandesa", R.drawable.verstappen, 27),
        Piloto(4, 2, "Yuki Tsunoda", 22, "Japonesa", R.drawable.tsunoda, 24),

        // Ferrari (ID: 3)
        Piloto(5, 3, "Lewis Hamilton", 44, "Británica", R.drawable.hamilton, 40),
        Piloto(6, 3, "Charles Leclerc", 16, "Monegasca", R.drawable.leclerc, 27),

        // McLaren (ID: 4)
        Piloto(7, 4, "Lando Norris", 4, "Británica", R.drawable.norris, 25),
        Piloto(8, 4, "Oscar Piastri", 81, "Australiana", R.drawable.piastri, 23),

        // Alpine (ID: 5)
        Piloto(9, 5, "Pierre Gasly", 10, "Francesa", R.drawable.gasly, 28),
        Piloto(10, 5, "Jack Doohan", 31, "Australiana", R.drawable.doohan, 21),

        // Aston Martin (ID: 6)
        Piloto(11, 6, "Fernando Alonso", 14, "Española", R.drawable.alonso, 43),
        Piloto(12, 6, "Lance Stroll", 18, "Canadiense", R.drawable.stroll, 26),

        // Williams (ID: 7)
        Piloto(13, 7, "Alexander Albon", 23, "Tailandesa", R.drawable.albon, 28),
        Piloto(14, 7, "Carlos Sainz", 55, "Española", R.drawable.sainz, 30),

        // Audi (ID: 8)
        Piloto(15, 8, "Nico Hülkenberg", 27, "Alemana", R.drawable.hulkenberg, 37),
        Piloto(16, 8, "Gabriel Bortoleto", 77, "Brasileña", R.drawable.bortoleto, 20),

        // Haas (ID: 9)
        Piloto(17, 9, "Esteban Ocon", 31, "Francesa", R.drawable.ocon, 28),
        Piloto(18, 9, "Oliver Bearman", 50, "Británica", R.drawable.bearman, 19),

        // Visa Cash App RB (ID: 10)
        Piloto(19, 10, "Liam Lawson", 30, "Neozelandesa", R.drawable.lawson, 22),
        Piloto(20, 10, "Isack Hadjar", 6, "Francesa", R.drawable.hadjar, 20),

        // Cadillac (ID: 11)
        Piloto(21, 11, "Checo Perez", 11, "Mexicana", R.drawable.perez, 35),
        Piloto(22, 11, "Valtteri Bottas", 77, "Finlandesa", R.drawable.bottas, 35)
    )

    fun getEscuderiaById(id: Int): Escuderia? = getEscuderias().find { it.id == id }
    fun getAutoByEscuderiaId(escuderiaId: Int): Auto? = getAutos().find { it.escuderiaId == escuderiaId }
    fun getPilotosByEscuderiaId(escuderiaId: Int): List<Piloto> = getPilotos().filter { it.escuderiaId == escuderiaId }
}