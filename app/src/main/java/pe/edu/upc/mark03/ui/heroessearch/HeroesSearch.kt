package pe.edu.upc.mark03.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import pe.edu.upc.mark03.factories.HeroRepositoryFactory
import pe.edu.upc.mark03.model.data.Hero
import pe.edu.upc.mark03.repository.HeroRepository
import pe.edu.upc.mark03.ui.herodetails.HeroDetails

@Composable
fun HeroesSearch(name: MutableState<String>,heroList: MutableState<List<Hero>> ,selectHero:(String)->Unit){



    Scaffold {paddingValues->
        Column(modifier = Modifier.padding(paddingValues)) {
            HeroSearch(name,heroList)
            HeroList(heroList,selectHero)
        }
    }
}

@Composable
fun HeroSearch(name: MutableState<String>, heroList: MutableState<List<Hero>>){

    val heroRepository = HeroRepositoryFactory.getHeroRepository()

    OutlinedTextField(
        value = name.value,
        modifier= Modifier
            .fillMaxWidth()
            .padding(2.dp),
        onValueChange = {
                        name.value=it
        },
        placeholder = {Text("Search hero")},
        leadingIcon = { Icon(Icons.Filled.Search,"Search hero") },//Icono de lupa
        keyboardOptions = KeyboardOptions(
            keyboardType= KeyboardType.Text,
            imeAction = ImeAction.Search//Boton de busqueda en el teclado del celular
        ),
        keyboardActions = KeyboardActions(//Cuando se presiona el boton de busqueda, se ejecuta la funcion
            onSearch = {
                heroRepository.searchHero(name.value){
                    heroList.value=it
                }
            }
        )
    )
}


@Composable
fun HeroList(heroList: MutableState<List<Hero>>, selectHero:(String)->Unit) {


    LazyColumn{
        items(heroList.value){hero->
            HeroCard(hero,selectHero)
        }
    }


}

@Composable
fun HeroCard(hero:Hero, selectHero:(String)->Unit){

    val isFavorite= remember{
        mutableStateOf(false)
    }

    isFavorite.value=hero.isFavorite

    val heroRepository = HeroRepositoryFactory.getHeroRepository()


    Card (modifier= Modifier
        .fillMaxWidth()
        .padding(4.dp),
        onClick = {
            selectHero(hero.id)
        }
        ){
        Row {
            HeroImage(hero.image.url, 92.dp)
            Column(modifier= Modifier
                .padding(4.dp)
                .weight(4f)){
                Text(text=hero.name, fontWeight = FontWeight.Bold)
                Text(text=hero.biography.fullName)

            }
            //coloca icono de favorito
            IconButton(onClick = {
                isFavorite.value=!isFavorite.value

                if (isFavorite.value){
                    heroRepository.insertHero(hero.id)
                    hero.isFavorite=true
                }else{
                    heroRepository.deleteHero(hero.id)
                    hero.isFavorite=false
                }


            }, modifier= Modifier.weight(1f)) {
                Icon(Icons.Filled.Favorite,"Favorite", tint = if(isFavorite.value) Color.Red else Color.Gray)
            }


        }
    }
}

@Composable
fun HeroImage(url: String , size: Dp){
   GlideImage(
       imageModel = { url},
       imageOptions= ImageOptions(contentScale= ContentScale.Crop),
       modifier= Modifier.size(size)
   )

}