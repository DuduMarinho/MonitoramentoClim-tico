async function getRealTimeWeather() {
    const location = document.getElementById('location').value;
    const url = `https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/${location}?key=C54CY4GKBL2CA49XWP5Y354NG`;

    try {
        const response = await fetch(url);
        const data = await response.json();
        displayWeather(data, 'weather-result');
    } catch (error) {
        console.error('Erro ao buscar dados do clima:', error);
    }
}

async function getWeatherHistory() {
    const location = document.getElementById('location').value;
    const startDate = document.getElementById('start-date').value;
    const endDate = document.getElementById('end-date').value;
    const url = `https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/${location}/${startDate}/${endDate}?key=C54CY4GKBL2CA49XWP5Y354NG`;

    try {
        const response = await fetch(url);
        const data = await response.json();
        displayWeather(data, 'history-result');
    } catch (error) {
        console.error('Erro ao buscar dados do histórico climático:', error);
    }
}

function displayWeather(data, elementId) {
    const container = document.getElementById(elementId);
    container.innerHTML = `
        <p>Localização: ${data.resolvedAddress}</p>
        <p>Temperatura: ${data.currentConditions.temp} °C</p>
        <p>Condição: ${data.currentConditions.conditions}</p>
    `;
}
