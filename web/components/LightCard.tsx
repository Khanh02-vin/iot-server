import { Power } from "lucide-react";
import { useState } from "react";

export default function LightCard() {
  const [status, setStatus] = useState<"ON" | "OFF">("OFF");
  const [loading, setLoading] = useState(false);

  const toggleLight = async () => {
    setLoading(true);

    const isOn = status === "OFF";
    const url = isOn
      ? "http://localhost:8080/api/light/on"
      : "http://localhost:8080/api/light/off";

    try {
      await fetch(url, { method: "POST" });
      setStatus(isOn ? "ON" : "OFF");
    } catch (err) {
      alert("Backend chưa chạy hoặc lỗi kết nối");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div
      style={{
        width: 300,
        padding: 20,
        borderRadius: 12,
        background: "#020617",
        border: "1px solid #1e293b",
      }}
    >
      <h2>Light</h2>

      <p style={{ margin: "10px 0", opacity: 0.8 }}>
        Status: <b>{status}</b>
      </p>

      <button
        onClick={toggleLight}
        disabled={loading}
        style={{
          display: "flex",
          alignItems: "center",
          gap: 8,
          padding: "10px 16px",
          borderRadius: 8,
          border: "none",
          cursor: "pointer",
          background: status === "ON" ? "#16a34a" : "#dc2626",
          color: "white",
        }}
      >
        <Power size={18} />
        {status === "ON" ? "Turn OFF" : "Turn ON"}
      </button>
    </div>
  );
}
  