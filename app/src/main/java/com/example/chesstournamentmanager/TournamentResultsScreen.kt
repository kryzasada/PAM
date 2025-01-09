package com.example.chesstournamentmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chesstournamentmanager.data.Player

@Composable
fun TournamentResultsScreen(
    results: Map<Player, Float>,
    system: String, // Dodano parametr systemu
    onRestartTournament: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Wyniki końcowe",
            style = MaterialTheme.typography.headlineSmall
        )

        if (system == "Pucharowy" && results.size == 1) {
            // Wyświetl tylko zwycięzcę w przypadku systemu pucharowego
            val winner = results.keys.first()
            Text(
                text = "Zwycięzca turnieju: ${winner.name}",
                style = MaterialTheme.typography.titleMedium
            )
        } else {
            // Wyświetl tabelę wyników w przypadku systemu szwajcarskiego
            results.entries.sortedByDescending { it.value }.forEach { (player, score) ->
                Text(text = "${player.name}: ${score} pkt")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Przycisk rozpoczęcia nowego turnieju
        Button(
            onClick = onRestartTournament,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Rozpocznij nowy turniej")
        }
    }
}

