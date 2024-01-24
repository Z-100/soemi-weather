import './App.css';
import {useState} from "react";

function App() {
    const [location, setLocation] = useState('');
    const [planet, setPlanet] = useState(null)

    const handleRestCall = () => {
        fetch(`http://sömi-weather.ch/api/get-the-star-wars-planets-mapping-for-the-current-temperature-completely-and-utterly-accurate?temp=${location}`)
            .then((response) => response.json())
            .then((data) => setPlanet(data))
            .catch((error) => console.error(error));
    };

    return (
        <div className="App">
            <input
                type="text"
                placeholder="Enter location"
                value={location}
                onChange={(e) => setLocation(e.target.value)}
            />
            <button onClick={handleRestCall}>Search</button>
            {planet !== null ? <Planet planet={planet}/> : <p>Please search for a location.</p>}
        </div>
    );
}

export function Planet({ planet }) {
    const {name, img} = planet

    return (
        <>
            <h1>Name: {name}</h1>
            <img src={img} alt="Star Wars Planet"/>
        </>
    );
}

export default App;
