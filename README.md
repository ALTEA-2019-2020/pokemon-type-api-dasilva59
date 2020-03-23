# pokemon-type-api-dasilva59
pokemon-type-api-dasilva59 created by GitHub Classroom


Microservice fonctionnel et déployé sur heroku à l'addresse https://pokemons-apielsa.herokuapp.com.
Un Swagger permettant des tests est disponible à l'addresse https://pokemons-apielsa.herokuapp.com/swagger-ui.html#. 
Les routes sont en mode GET:
 -  /pokemon-types/ retourne tous les pokemons-types.
 
 - /pokemon-types/{id} retourne le pokemon-type de l'identifiant id
 
 - /pokemon-types avec deux pathparameters name et types de type string. Cela permet soit une recherche par nom de pokemon quand le name est renseigné ou par type. Le parâmètre type est de de type string mais peu en regroupé plusieurs. Exemple types="typa1,type2...."
 Seuls les pokemons possédant tous ces types seront retournés. 


une collection postman pokemon-type-api.postman_collection.json peut être trouvée dans src/test/resources.
