<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Weather App</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        * {
            margin: 0; padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body {
            background: linear-gradient(135deg, #74ebd5, #9face6);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .card {
            background: white;
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.2);
            text-align: center;
            width: 350px;
            animation: fadeIn 1s ease;
        }

        .card h1 {
            font-size: 28px;
            color: #333;
        }

        .weather-icon {
            width: 100px;
            height: 100px;
            margin: 20px auto;
        }

        .info {
            text-align: left;
            margin-top: 20px;
        }

        .info p {
            font-size: 16px;
            margin: 8px 0;
        }

        @keyframes fadeIn {
            from {opacity: 0; transform: translateY(20px);}
            to {opacity: 1; transform: translateY(0);}
        }

        .footer {
            font-size: 12px;
            margin-top: 20px;
            color: #888;
        }
    </style>
</head>
<body>
<div class="card">
    <h1>${city}</h1>
    <img src="https://openweathermap.org/img/wn/10d@2x.png" alt="Weather Icon" class="weather-icon" />

    <div class="info">
        <p><strong>Date:</strong> ${date}</p>
        <p><strong>Temperature:</strong> ${tempreature}°C</p>
        <p><strong>Condition:</strong> ${weathercondition}</p>
        <p><strong>Humidity:</strong> ${humidity}%</p>
        <p><strong>Wind Speed:</strong> ${windspeed} m/s</p>
        
        <img src="https://openweathermap.org/img/wn/${icon}@2x.png" alt="Weather Icon" class="weather-icon" />
    </div>

    <div class="footer">
        Powered by OpenWeatherMap API
    </div>
</div>
</body>
</html>
