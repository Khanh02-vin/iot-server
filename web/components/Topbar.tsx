import { Bell } from "lucide-react";

export default function Topbar() {
  return (
    <header className="h-16 bg-white shadow flex items-center justify-between px-6">
      <input
        className="border rounded px-3 py-1 w-64"
        placeholder="Search..."
      />

      <div className="flex items-center gap-4">
        <Bell />
        <div className="w-8 h-8 bg-gray-300 rounded-full" />
      </div>
    </header>
  );
}
