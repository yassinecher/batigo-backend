from fastapi import FastAPI
from pydantic import BaseModel
import joblib
import numpy as np
from sklearn.preprocessing import StandardScaler

app = FastAPI()

 # Charger le modèle et le scaler
model = joblib.load("decision_tree_model.joblib")
scaler = joblib.load("scaler.joblib")

class InputData(BaseModel):
     type_projet: int
     budget_estime: float
     duree_estimee: float
     incident_qualite: int
     incident_securite: int
     materiaux_defectueux: int
     conditions_meteo: int

@app.post("/predict-budget")
def predict_budget(data: InputData):
     X_new = np.array([[data.type_projet, data.budget_estime, data.duree_estimee,
                        data.incident_qualite, data.incident_securite,
                        data.materiaux_defectueux, data.conditions_meteo]])
     X_new_scaled = scaler.transform(X_new)
     prediction = model.predict(X_new_scaled)[0]
     return {"budget_reel_prevu": prediction}
