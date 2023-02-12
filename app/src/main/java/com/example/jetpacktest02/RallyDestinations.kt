/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetpacktest02

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector


/**
 * Contract for information needed on every Rally navigation destination
 */
interface RallyDestination {
    val icon: ImageVector
    val route: String
}

/**
 * Rally app navigation destinations
 */
object Overview : RallyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "overview"
}

object Accounts : RallyDestination {
    override val icon = Icons.Filled.AttachMoney
    override val route = "accounts"
}

object Bills : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "Bills"
}

object Plant : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "1.1-Plant"
}

object PlantPlan : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "1.2-plant-plan"
}
object ChooseSeed : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "3.1-choose-seed"
}

object Dailyhealthmessage : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "1.3-Dailyhealthmessage"
}

object IslandChooseIsland : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "4.1-island-chooseIsland"
}
object Island : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "4.2-island"
}
object IslandExplore : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "4.2-islandExplore"
}

object IslandMemberList : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "4.3-island-memberList"
}
object IslandNearbyMemberList : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "4.3-island-nearbyMemberList"
}
object IslandVisitOther : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "4.5-island-visitOther/{res}/{name}"
}

object IslandDeliver : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "4.6-island-deliver"
}

object Message : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "2.1-message"
}
object MessageMsg : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "2.3-message-message"
}

object MessageTap : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "2.2-message-tap"
}

object MessagePic : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "2.4-message-picture"
}

object MessageFriend : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "2.5-message-friend"
}

object MessageID : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "2.6-message-friendID"
}

object My : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "7.0-My"
}

object PlantBagPossessed : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "5.1.1-PlantBag-possessed"
}

object PlantFoot : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "1.2.1-plant-foot"
}

object VipUnsigned : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "8.1-Vip（unsigned）"
}

object PlantLookingForPlanFoot : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "1.4.1-plant-lookingforplan-foot"
}

object HealthConclusion : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "6.1_conclusion_thisweek"
}

object PlanList : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "plant-finish a plan"
}
object SetPlanSports : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "plant-sports"
}
object SetPlanDrink : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "1.2.4-plant-drink"
}
object SetPlanSleep : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "1.2.7-plant-sleeping"
}
object SetPlanEating : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "1.2.6-plant-eating"
}
object SetPlanDiy : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "1.2.5-plant-diyplan"
}
object PlanListAdded : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "1.2-plant-dailyplan"
}

object Test : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "test"
}
object HealthShare : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "6.3_Share"
}

object HealthPast : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "6.2_conclusion_past"
}

object HealthTabTest : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "6test"
}

object MyCupBoard : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "7.2-cupboard"
}



// Screens to be displayed in the top RallyTabRow
val rallyTabRowScreens = listOf(Overview, Accounts,Plant,PlantPlan,Dailyhealthmessage)
