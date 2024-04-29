package pe.edu.upc.mark03.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.edu.upc.mark03.model.data.Hero
import pe.edu.upc.mark03.ui.herodetails.HeroDetails

@Composable
fun Home(){

    //naviagtion
    val navController = rememberNavController()

    val name = remember{
        mutableStateOf("")
    }
    val heroList = remember{
        mutableStateOf(emptyList<Hero>())
    }
    NavHost(navController = navController, startDestination = Routes.HeroesSearch.route){
        composable(Routes.HeroesSearch.route){
            HeroesSearch(name,heroList){
                navController.navigate("${Routes.HeroDetail.route}/$it")
            }
        }
        composable(Routes.HeroDetail.routeWithArgument, arguments = listOf(navArgument(name=Routes.HeroDetail.argument){
            type= NavType.StringType
         }))
        { navBackStackEntry ->
            val id= navBackStackEntry.arguments?.getString(Routes.HeroDetail.argument) as String
            HeroDetails(id)
        }
        
    }


    }


sealed class Routes(val route:String){
    data object HeroesSearch: Routes("HeroesSearch")
    data object HeroDetail: Routes("HeroDetails"){
        const val routeWithArgument="HeroDetails/{id}"
        const val argument="id"
    }
}
