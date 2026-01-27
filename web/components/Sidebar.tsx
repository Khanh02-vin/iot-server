import { Home, BarChart2, Settings } from "lucide-react";

export default function Sidebar() {
  return (
    <aside className="w-64 bg-white shadow-lg">
      <div className="p-6 text-xl font-bold border-b">
        IoT Dashboard
      </div>

      <nav className="p-4 space-y-3">
        <MenuItem icon={<Home />} label="Dashboard" />
        <MenuItem icon={<BarChart2 />} label="Analytics" />
        <MenuItem icon={<Settings />} label="Settings" />
      </nav>
    </aside>
  );
}

function MenuItem({ icon, label }: any) {
  return (
    <div className="flex items-center gap-3 p-3 rounded-lg cursor-pointer hover:bg-gray-100">
      {icon}
      <span>{label}</span>
    </div>
  );
}
