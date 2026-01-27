import LightCard from "../components/LightCard";

export default function Dashboard() {
  return (
    <div style={{ padding: 40 }}>
      <h1>IoT Dashboard</h1>
      <p style={{ opacity: 0.7, marginBottom: 20 }}>
        EMQX + Spring Boot + React
      </p>

      <LightCard />
    </div>
  );
}
