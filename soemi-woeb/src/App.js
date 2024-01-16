import './App.css';
import {useState} from "react";

function App() {
    const [location, setLocation] = useState('');
    const [planet, setPlanet] = useState(null)

    const handleRestCall = () => {
        fetch(`https://localhost/soemi-woers/get-the-star-wars-planets-mapping-for-the-current-temperature-completely-and-utterly-accurate?temp=${location}`)
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
                setPlanet(data)
            })
            .catch((error) => {
                console.error(error);
            });
    };

    return (
        <div className="App">
            <input
                type="text"
                placeholder="Enter location"
                value={location}
                onChange={(e) => setLocation(e.target.value)}
            />
            <button onClick={handleRestCall}>Get Weather</button>
            <p>Planet name: {planet?.name}</p>
        </div>
    );
}

export default App;
