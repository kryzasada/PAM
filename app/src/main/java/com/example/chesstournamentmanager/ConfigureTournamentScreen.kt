package com.example.chesstournamentmanager

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.example.chesstournamentmanager.data.Player
import kotlin.math.ceil
import kotlin.math.log2

@Composable
fun ConfigureTournamentScreen(
    selectedPlayers: List<Player>,
    onStartTournament: (String, Int, String) -> Unit, // Dodajemy system remisów
    onBackToSelectPlayers: () -> Unit
) {
    var selectedSystem by remember { mutableStateOf("Szwajcarski") }
    var selectedTieBreakMethod by remember { mutableStateOf("Progres") } // Domyślnie Progres
    var isSystemDropdownExpanded by remember { mutableStateOf(false) }
    var isTieBreakDropdownExpanded by remember { mutableStateOf(false) }

    // Obliczanie liczby rund automatycznie
    val calculatedRounds = if (selectedSystem == "Szwajcarski" || selectedSystem == "Pucharowy") {
        ceil(log2(selectedPlayers.size.toDouble())).toInt()
    } else {
        1
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Konfiguracja turnieju", style = MaterialTheme.typography.headlineSmall)
        Text("Wybrano zawodników: ${selectedPlayers.size}")

        // Wybór systemu turnieju
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "System turnieju:")
            Button(
                onClick = { isSystemDropdownExpanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(selectedSystem)
            }
            DropdownMenu(
                expanded = isSystemDropdownExpanded,
                onDismissRequest = { isSystemDropdownExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Szwajcarski") },
                    onClick = {
                        selectedSystem = "Szwajcarski"
                        isSystemDropdownExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Pucharowy") },
                    onClick = {
                        selectedSystem = "Pucharowy"
                        isSystemDropdownExpanded = false
                    }
                )
            }
        }

        // Wybór systemu remisów
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "System remisów:")
            Button(
                onClick = { isTieBreakDropdownExpanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(selectedTieBreakMethod)
            }
            DropdownMenu(
                expanded = isTieBreakDropdownExpanded,
                onDismissRequest = { isTieBreakDropdownExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Progres") },
                    onClick = {
                        selectedTieBreakMethod = "Progres"
                        isTieBreakDropdownExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Sumaryczny") },
                    onClick = {
                        selectedTieBreakMethod = "Sumaryczny"
                        isTieBreakDropdownExpanded = false
                    }
                )
            }
        }

        // Przycisk rozpoczęcia turnieju
        Button(
            onClick = { onStartTournament(selectedSystem, calculatedRounds, selectedTieBreakMethod) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Rozpocznij turniej")
        }

        // Przycisk powrotu
        Button(
            onClick = onBackToSelectPlayers,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Powrót do wyboru zawodników")
        }
    }
}


