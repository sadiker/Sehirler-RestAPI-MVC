let cityIndex = 0;
const cities = [];

fetch("http://localhost:8080/cities")
  .then(response => response.json())
  .then(data => {
    cities.push(...data);
    setInterval(showNextCity, 700);
  });

function showNextCity() {
  const city = cities[cityIndex];
  document.getElementById("city-image").src = city.image;
  document.getElementById("city-name").textContent = city.name;
  document.getElementById("city-region").textContent = city.region;
  document.getElementById("city-plate").textContent = city.plate;

  cityIndex = (cityIndex + 1) % cities.length;
}