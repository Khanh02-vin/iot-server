import { Power } from "lucide-react";
import { useState } from "react";

export default function LightSwitch() {
  const [on, setOn] = useState(false);

  const toggle = async () => {
    const next = !on;
    setOn(next); // đổi UI trước

    const url = next
  ? "http://localhost:8080/api/led/on"
  : "http://localhost:8080/api/led/off";
    try {
      const res = await fetch(url, { method: "POST" });
      if (!res.ok) throw new Error("API error");
    } catch (err) {
      alert("Backend lỗi, kiểm tra server");
      setOn(!next); // rollback
    }
  };

  return (
    <button
      onClick={toggle}
      style={{
        padding: 16,
        fontSize: 18,
        background: on ? "#22c55e" : "#ef4444",
        color: "white",
        border: "none",
        borderRadius: 12,
        display: "flex",
        gap: 8,
        alignItems: "center",
      }}
    >
      <Power />
      {on ? "LIGHT ON" : "LIGHT OFF"}
    </button>
  );
}
