import './App.css';
import {useState} from "react";

function App() {
    const [location, setLocation] = useState('');
    const [planet, setPlanet] = useState(null)

    const handleRestCall = async () => {
        const temp = await getTemp();
        const imgRes = await fetch(`http://sömi-weather.ch/api/get-the-star-wars-planets-mapping-for-the-current-temperature-completely-and-utterly-accurate?temp=${temp}`)
        console.log(imgRes);
        const imgJson = await imgRes.json();
        console.log(imgJson);
        setPlanet(imgJson);
    };

    const getTemp = async () => {
        const res = await fetch(`http://xn--smi-weather-rfb.ch/moen/engine-rest/process-definition/key/Process_13h4fbi/start`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(
                {
                    "businessKey": "yourBusinessKey",
                    "variables": {
                        "location": {"value": location, "type": "String"}
                    }
                }
            )
        });
        console.log(res);
        const json = await res.json();
        console.log(json);
        const id = await json.id;
        console.log(id);

        for(let i = 0; i < 10; i++)
        {
            await delay(500);
            try
            {
                const varRes = await fetch(`http://xn--smi-weather-rfb.ch/moen/engine-rest/history/variable-instance?processInstanceId=${id}&variableName=temp`)
                console.log(varRes);
                const varJson = await varRes.json();
                console.log(varJson);
                const temp = varJson[0].value;
                console.log(temp);

                return temp;               
            }
            catch (err)
            {
                console.log(err);
                console.log("retrying");
            }
        }
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

function delay(time) {
    return new Promise((resolve) => {
        setTimeout(() => resolve(), time);
    });
}


export function Planet({ planet }) {
    const {name, image} = planet

    return (
        <>
            <h1>Name: {name}</h1>
            <img src={image} alt="Star Wars Planet"/>
        </>
    );
}

export default App;
