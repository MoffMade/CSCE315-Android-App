CSCE315-Android-App
===================

  The purpose of the Constellation Explorer app is to have the user guide a spaceship from one celestial body to another within a given constellation by dodging space debris and learn about the celestial body upon arrival at the star. Upon starting a game, the user is shown a map of a certain constellation and will be able to choose a destination among available stars. Once chosen, the player must guide his spaceship between asteroids and other debris in route to the star. If the ship collides with too many objects, the ship will be destroyed and they must start over. If the ship survives the travel and reaches the destination star, the player will be presented with an image of the star as well as other astronomical data such as the star’s name, position in the night sky and distance from earth. Once a star has been visited, the player can choose to play the game again or simply view the star data. Ranks based on how many stars have been visited or multiple choices for constellations may be implemented, depending on development time. At least one constellation will be implemented for this project.

  The star data and the constellation maps will be gathered from SKY-MAP.org, which provides a source of information aggregated from many surveys done of the universe around us. The data is provided through an online API which returns an XML resource containing the star’s data provided by the astrological surveys that make up the sky-map.org database. This game is meant to provide a fun way to learn about the other stars and celestial bodies in the night sky.

Scientific Data from: http://server3.sky-map.org/?locale=US
Instructions to pull star XML data: http://server3.sky-map.org/XML_API_V1.0.html
