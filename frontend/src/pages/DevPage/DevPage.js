
import { ModalBtn } from '../../common/presenters/ModalBtn'
import { faBook, faAddressCard, faAnchor } from '@fortawesome/free-solid-svg-icons'
import { useState } from 'react';
import { TaskForm } from "../../Model/Task";
import { plan } from "../../mock/MockPlan";
import { PlanCard } from '../../Model/Plan/PlanPresenters'



export default function AddTaskModal() {
  const [ mockPlan, setPlan ] = useState(plan)
  return (

    <main style={{ display: "flex", "justifyContent": "center", backgroundColor: '#F1E0C5', 'minHeight': '100vh' }}>
      <div style={{width: "900px"}}>
        <PlanCard
            plan={mockPlan}
          />
      </div>
    </main>
  );

}

// function PlanStatusWidget(props) {
//   return (
//     <div>

//     </div>
//   )
// }

